from django.db import models
from datetime import datetime

def get_current_date():
    return datetime.now()

# Create your models here.
class Score(models.Model):
    """
    Score model, if possible links to AraPheno
    """
    user = models.CharField(max_length=255)
    score = models.FloatField(blank=True)
    date = models.DateTimeField(default=get_current_date) # date of creation/update

    def __str__(self):
        return "{}: {}".format(self.user, self.score)