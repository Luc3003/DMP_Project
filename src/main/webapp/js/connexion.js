let Incorrect;

window.onload = function () {
    let bouton = document.getElementById("bouton1");
    bouton.onclick = function (){
        usuerOrMdpIncorrect();

    };
};

function Validation(){
    if(Incorrect){
        let myError = document.getElementById("error");
        myError.innerHTML="L' identifiant ou le mot de passe est incorrect";
        myError.style.color='red';
        return false;
    }else{
        let myError = document.getElementById("error");
        myError.innerHTML="";
        return true;
    }
}


function usuerOrMdpIncorrect(){//cette méthode retourne true que l'email soit déjà existant ou non.
    let request = new XMLHttpRequest();
    request.open("POST","http://localhost:8080/connexion/Incorrect",true);
    request.responseType="text";
    request.onload = function (){
        if(this.response=="true"){
            Incorrect= true;
        }else{
            Incorrect= false;
        }
    }
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("username="+document.getElementById("username").value+
    "&password="+document.getElementById("password").value);
};