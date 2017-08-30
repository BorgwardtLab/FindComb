from leaderboard.models import Score
from rest_framework import serializers


class ScoreSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Score
        fields = ('user', 'score', 'date', 'level')
