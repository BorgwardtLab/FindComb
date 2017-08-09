from rest_framework import status, viewsets, permissions
from rest_framework.response import Response
from rest_framework.renderers import JSONRenderer
from rest_framework.decorators import api_view, detail_route, list_route

from django.db.models import Q

from leaderboard.models import Score
from leaderboard.serializers import ScoreSerializer


@api_view(['GET', 'POST'])
def score_list(request):
    """
    List all snippets, or create a new snippet.
    """
    if request.method == 'GET':
        snippets = Score.objects.all()
        serializer = ScoreSerializer(snippets, many=True)
        return Response(serializer.data)

    elif request.method == 'POST':
        serializer = ScoreSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class ScoreViewSet(viewsets.ModelViewSet):
    """
    API for scores

    list:
    Lists all stored scores

    retrieve:
    Retrieves information about a specific score

    create:
    add a new score to the leaderboard

    top:
    Lists the top 10 scores (for specific set of parameters, e.g. date)

    post:

    """
    queryset = Score.objects.all()
    serializer_class = ScoreSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)

    # Overriding get_queryset to allow for case-insensitive custom ordering
    def get_queryset(self):
        queryset = self.queryset
        ordering = '-score'
        from django.db.models.functions import Lower
        inverted = False
        if ordering.startswith('-'):
            ordering = ordering[1:]
            inverted = True
        ordering = Lower(ordering)
        queryset = queryset.order_by(ordering)
        if inverted:
            queryset = queryset.reverse()
        return queryset

    @detail_route(methods=['GET'], url_path='ranking')
    def get_ranking(self, request, pk):
        """Returns the ranking of a user """
        current_score = Score.objects.get(pk=pk)
        print(request.user)
        id = current_score.pk
        points = current_score.score
        index = Score.objects.filter(
            Q(score__gt=points) |
            Q(score=points, pk__lt=id)
        ).count()
        n_users = Score.objects.all().count()

        scores = Score.objects.all().order_by('-score', 'pk')[max(0,index - 5):min(index + 6,n_users)]
        serializer = ScoreSerializer(scores, many=True)
        results = {'ranking':index, 'scores':serializer.data, 'indices':list(range(max(0,index - 5),min(index + 6,n_users)))}
        return Response(results)

    @list_route(methods=['GET'], url_path='top_n')
    def top(self, request):
        N = request.query_params.get('n')
        print(N)
        if N != None and N != '':
            try:
                N = int(N)
            except ValueError:
                print(ValueError)
                N=10
        else:
            N=10
        queryset = self.get_queryset()
        serializer = ScoreSerializer(queryset[:N],many=True)
        return Response(serializer.data)

