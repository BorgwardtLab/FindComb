# -*- coding: utf-8 -*-
# Generated by Django 1.11.3 on 2017-08-02 12:26
from __future__ import unicode_literals

from django.db import migrations, models
import leaderboard.models


class Migration(migrations.Migration):

    dependencies = [
        ('leaderboard', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='score',
            name='date',
            field=models.DateTimeField(default=leaderboard.models.get_current_date),
        ),
    ]
