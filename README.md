# FindComb
Scientifica app for finding the most significant combinations of features

## Install server
You can find the server development version under findcomb_server. In order to set up the server locally, it is recommended to build a python 3 virtualenv with the following command:
```scripting
virtualenv -p python3 yourvenvname
```
Once your virtual environment is set, activate it (run source yourvenvname/bin/activate) and install the dependencies with
```scripting
pip install -r requirements.txt
```
You're now set to use the server.
## Run server
To run the server on the local port 8000, run
```scripting
./manage.py runserver
```
