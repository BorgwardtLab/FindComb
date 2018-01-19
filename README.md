# FindComb
Scientifica app for finding the most significant combinations of features

## Presentation at WEF. 
Download software for touchscreen at https://www.solutions4av.com/de/produkte/primetouch-4/.  
Enable key shortcut for zoom in accessablility menue in system preferences.  
```
Option+Command+'=' -> Zoom in.   
Option+Command+'-' -> Zoom out. 
```

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
