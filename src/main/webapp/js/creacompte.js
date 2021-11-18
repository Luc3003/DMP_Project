let emailNotExist;

window.onload = function () {
  let bouton = document.getElementById("bouton");
  bouton.onclick = function (){
      console.log(Validation());
      Validation();
      dateEstAvtMtn()
      emailNotAlreadyExist();
      NumSecuIsCorrect();
      NumTelIsCorrect();
      emailIsCorret();
      emailCorrect();


  }
};


function siVide(idElem){
    let myInput = document.getElementById(idElem);
    if(myInput.value.trim()==""){
        return false;
    }else{
        return true;
    }
}
function AucunChampsVide() {
    let formok = true;

    formok = formok && siVide("nom");
    formok = formok && siVide("Prenom");
    formok = formok && siVide("Adresse");
    formok = formok && siVide("Email");
    formok = formok && siVide("Telephone");
    formok = formok && siVide("NumeroSecu");
    formok = formok && siVide("DateNaissance");
    formok = formok && siVide("MotdePasse");

    return formok;

};

function emailCorrect(){
    if(emailIsCorret()&&emailNotExist){
        let myError = document.getElementById("error_Email");
        myError.innerHTML="";
    }else if(!emailIsCorret()&&emailNotExist){
        let myError = document.getElementById("error_Email");
        myError.innerHTML="L'adresse mail ci-dessus est erronée";
        myError.style.color='red';
    }else{
        let myError = document.getElementById("error_Email");
        myError.innerHTML="L'adresse mail existe déjà";
        myError.style.color='red';
    }
}


function emailIsCorret() {
    var expressionReguliere = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,6}))$/;
    let email = document.getElementById("Email").value;
    if (expressionReguliere.test(email)) {
        return true;
    } else {
        return false;
    }
};

function NumSecuIsCorrect() {
    let numsecu = document.getElementById("NumeroSecu").value;
    if (isNaN(numsecu)) {
        let myError = document.getElementById("error_NumSecu");
        myError.innerHTML="Le numéro de Sécurité sociale est incorrecte";
        myError.style.color='red';
        return false;
    } else {
        let myError = document.getElementById("error_NumSecu");
        myError.innerHTML="";
        return true;
    }
};

function NumTelIsCorrect() {
    var contrainte = /^[0-9]+$/;
    let numTel = document.getElementById("Telephone").value;
    if (contrainte.test(numTel)) {
        let myError = document.getElementById("error_Tel");
        myError.innerHTML="";
        return true;
    }
    else {let myError = document.getElementById("error_Tel");
        myError.innerHTML="Le numéro de téléphone est incorrecte";
        myError.style.color='red';
        return false;}
};


//oreganiser les methodes pour plus de comprhenseion et ajouter des commentaires
function Validation(){
    let toutEstCorret;
    console.log("emailNotAlreadyExist()"+emailNotExist);
    toutEstCorret=emailIsCorret()&&AucunChampsVide()&&NumTelIsCorrect()&&NumSecuIsCorrect()&&emailNotExist&&dateEstAvtMtn();
    if(toutEstCorret){return true;}//si on met &&!emailNotAlreadyExist() au dessus l'inscription fctne
        //mais plus le non cahngement de page.
    else{return false;}
};

function emailNotAlreadyExist(){
    let request = new XMLHttpRequest();
    request.open("POST","http://localhost:8080/creercompte/emailAlreadyExist",true);
    request.responseType="text";
    request.onload = function (){

        if(request.response=="true"){
            emailNotExist= true;
        }else{
            emailNotExist= false;
        }
    }
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("email="+document.getElementById("Email").value);
};


function dateEstAvtMtn(){
    let dateUser = Date.parse(document.getElementById("DateNaissance").value);
    let now=Date.now();
    if(dateUser<now){
        let myError = document.getElementById("error_Date");
        myError.innerHTML="";
        return true;
    }else {
        let myError = document.getElementById("error_Date");
        myError.innerHTML="La date de naissance est impossible";
        myError.style.color='red';
        return false;
    }
}

/*function afficheEstVide(idElem, errorId){
    if(!siVide(idElem)){
        let myError = document.getElementById(errorId);
        myError.innerHTML="Le champs ci-dessus est requis";
        myError.style.color='red';
    }else{
        let myError = document.getElementById(errorId);
        myError.innerHTML="";
    }

}

function afficheErreurVidePourToutChamps(){
    afficheEstVide("nom", "error_Nom");
    afficheEstVide("Prenom", "error_Prenom");
    afficheEstVide("Adresse", "error_Adresse");
    afficheEstVide("Email", "error_Email");
    afficheEstVide("Telephone", "error_Tel");
    afficheEstVide("NumeroSecu", "error_NumSecu");
    afficheEstVide("DateNaissance", "error_DateNaissance");
    afficheEstVide("MotdePasse", "error_Pwd");
}*/

/*/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,6}))$/*/