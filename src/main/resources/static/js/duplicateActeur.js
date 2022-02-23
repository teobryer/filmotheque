var i = 0;

function duplicate() {
    i++;

    var div = document.getElementsByClassName('listeActeurs')[0];
    var original = document.getElementsByClassName('acteurLine')[0];
    var clone = original.cloneNode(true) // "deep" clone




 //
  //  clone.removeAttribute("hidden");


    console.log( clone);
    clone.classList.remove("acteurLine");
    clone.removeAttribute("hidden");

 //  clone.getElementsByClassName("acteurLine")[0].removeAttribute("hidden");
   // clone.getElementsByClassName("acteurLine")[0].removeClass("acteurLine");

    var nom = clone.getElementsByClassName("nom")[0];
    var prenom = clone.getElementsByClassName("prenom")[0];



    nom.name = "acteurs["+i+"].nom";
    prenom.name = "acteurs["+i+"].prenom";


    div.appendChild(clone);
}