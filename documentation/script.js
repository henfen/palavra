var knowElements = ["whatIsArticle", "howToUseArticle", "designArticle", "sourceArticle", "default"];


function hideAll(elements) {
    for (var i = 0; i < knowElements.length; i++){
        document.getElementById(knowElements[i]).style.display = 'none';
    }
}

function displayContent(theId) {
    hideAll(knowElements);
    document.getElementById(theId).style.display = 'block';
    
}