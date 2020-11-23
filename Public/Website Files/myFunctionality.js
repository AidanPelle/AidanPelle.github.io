document.getElementById("body").style.paddingTop = document.getElementById("header").offsetHeight + "px";
document.getElementById("body").style.paddingBottom = document.getElementById("footer").offsetHeight + "px";
var windowWidth = window.innerWidth;

function loadAboutMe() {
    clearSummaryImage();

    const aboutMeText = "I love programming both as a career and a hobby."
    + " I've worked at a coffee shop, web devlopment, and programming tutoring." 
    + " I graduated from high school with a 4.0, and am currently enrolled at SAIT with a 4.0 GPA."
    + " I love to read, play DnD, and swim.";
    document.getElementById("summary").innerHTML = aboutMeText;

    let divSummary = document.getElementById("summary");
    divSummary.style.textAlign = "left";
    divSummary.style.width = "35%";
    divSummary.style.marginTop = "50px";
    const imageContainer = document.createElement("div");
    divSummary.prepend(imageContainer);

    var image = 0;
    if (windowWidth < 800) {
        image = createImage("../images/Profile Picture.jpg", 400, 400);
    }
    else {image = createImage("../images/Profile Picture.jpg", 200, 200);}
    image.style.marginRight = "20px";
    image.style.borderRadius = "20px";
    image.style.float = "left";
    imageContainer.append(image);

    let root = document.getElementById("skills-list");
    while( root.firstChild ){
        root.removeChild( root.firstChild );
    }
}

function loadPortfolio() {
    clearSummaryImage();
    resetSummary();

    let portfolioText = "As a first-year student at SAIT, I'm still learning all that I can about programming."
    + " However, I love to learn and I plan on picking up as many skills as I can."
    + " Here's a basic calculator app, along with a playable RPG game (both made in JavaScript)."
    + " I've also built this website.";
    document.getElementById("summary").innerHTML = portfolioText;

    let root = document.getElementById("skills-list");
    while( root.firstChild ){
        root.removeChild( root.firstChild );
    }

    root.appendChild(createLink("#", "../images/Videogame.png", 
    "../images/VideogameHover.png", 100, 75, "portfolioBox", false, "Game"));

    root.appendChild(createLink("../../Portfolio/Calculator/calc.html", "../images/Calculator.png",
    "../images/CalculatorHover.png", 100, 125, "portfolioBox", false));

    root.appendChild(createLink("../../Portfolio/Pen Website/pen.html", "../images/Website.png",
    "../images/Website Hover.png", 80, 80, "portfolioBox", false, "Website"));
}

function loadResumeSummary() {
    clearSummaryImage();
    resetSummary();

    let resumeSummary = "Software Development Student at SAIT with leadership experience, web development knowledge"
    + " and general programming skills. I've completed a website (working on two others) and a videogame written"
    + " in both Java and JavaScript. I have a passion for programming and I'm looking to further my skills"
    + " in an internship position.";
    document.getElementById("summary").innerHTML = resumeSummary;
    
    let root = document.getElementById("skills-list");
    while( root.firstChild ){
        root.removeChild( root.firstChild );
    }

    /*Creating Resume icon and adding to ul*/
    root.appendChild(createLink("../../Portfolio/Resume.pdf", "../images/ResumeDownload.png",
    "../images/ResumeHover.png", 75, 75, "resumeBox", true, "Resume"));

    /*Creating CV icon and adding to ul*/
    root.appendChild(createLink("../../Portfolio/Resume.pdf", "../images/CVDownload.png", 
    "../images/CVHover.png", 100, 120, "resumeBox", true));

    document.getElementById("margin3").style.height = "30px";
}

function createIcon(src, width, height, boxType, text) {
    const iconDownload = document.createElement("li");
    iconDownload.classList.add(boxType);

    /*Creating div container and adding to li*/
    const iconContainer = document.createElement("div");
    iconDownload.appendChild(iconContainer);

    /*Creating image element and adding to div container*/
    const iconImage = createImage(src, width, height);
    if (text != undefined)
        iconContainer.textContent = text;
    iconContainer.prepend(iconImage);

    return iconDownload;
}

function createLink(link, imgSrc, hoverSrc, width, height, boxType, isDownload, text) {
    const iconDownload = document.createElement("li");
    iconDownload.classList.add(boxType);

    /*Creating div container and adding to li*/
    const iconContainer = document.createElement("a");
    iconContainer.href = link;
    if (isDownload==true){
        iconContainer.setAttribute('download',"download=" + text);
    }
    iconDownload.appendChild(iconContainer);

    /*Creating image element and adding to div container*/
    const iconImage = createImage(imgSrc, width, height);
    if (text != undefined) {
        iconContainer.textContent = text;
    }

    const hoverImage = createImage(hoverSrc, width, height);
    if (text != undefined) {
        iconContainer.textContent = text;
    }
    
    iconContainer.onmouseover = function() {iconContainer.removeChild(iconContainer.firstChild); iconContainer.prepend(hoverImage)};
    iconContainer.onmouseout = function() {iconContainer.removeChild(iconContainer.firstChild); iconContainer.prepend(iconImage)};
    iconContainer.prepend(iconImage);

    return iconDownload;
}

function createImage(imageSource, width, height) {
    const image = document.createElement("img");
    image.src = imageSource;
    image.width = width;
    image.height = height;
    return image;
}

function clearSummaryImage() {
    let root = document.getElementById("summary");
    if (root.firstChild.hasChildNodes()) {
        root.removeChild(root.firstChild);
    }
}

function loadHomePage() {
    location.reload();
return false;
}

function isImage(i) {
    return i instanceof HTMLImageElement;
}

function resetSummary() {
    var summary = document.getElementById("summary");
    summary.style.textAlign = "center";
    if (windowWidth < 800) {
        summary.style.marginTop = "75px";
        summary.style.width = "75%";
    }
    else {
        summary.style.marginTop = "110px";
        summary.style.width = "50%";
    }
}