
Git Configuration
git config --global user.name "Boobathi Ayyasamy"
git config --global user.email boobathi.ayyasamy@gmail.com
git config --global core.editor "code --wait"
git config --global core.autocrlf true


url
***
http://localhost:8081/currency/exchange

Request
*******
{
    "from" :"USD",
    "to":"INR"
}


Response
********
{
    "from": "USD",
    "to": "INR",
    "value": 77.951504
}

