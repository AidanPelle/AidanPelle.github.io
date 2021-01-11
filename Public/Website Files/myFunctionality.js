document.getElementById("body").style.paddingTop = document.getElementById("header").offsetHeight + "px";
document.getElementById("body").style.paddingBottom = document.getElementById("footer").offsetHeight + 5 + "px";
var windowWidth = window.innerWidth;
var divSummary = document.getElementById("summary");
var divTitle = document.getElementById("title");
if (windowWidth > 800) {
    loadFooterHovers();
}

function loadAboutMe() {
    clearSummaryImage();
    resetSummary();
    resetTitle();

    const aboutMeText = "I love programming both as a career and a hobby."
    + " I've worked as a barista, web developer, and stock worker." 
    + " I graduated high school with a 4.0 GPA, and am currently enrolled at SAIT with a 4.0."
    + " I love to read, play DnD, and swim.";
    document.getElementById("summary").innerHTML = aboutMeText;

    divSummary.style.textAlign = "left";
    divSummary.style.marginTop = "10px";
    const imageContainer = document.createElement("div");
    divSummary.prepend(imageContainer);

    var image = 0;
    if (windowWidth < 800) {
        divTitle.style.marginTop = "45px";
        image = createImage("../images/Profile Picture.jpg", 120, 120);
    }
    else {
        image = createImage("../images/Profile Picture.jpg", 200, 200);
        divSummary.style.width = "35%";
    }
    image.style.marginRight = "15px";
    image.style.borderRadius = "15px";
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
    resetTitle();

    let root = document.getElementById("skills-list");
    while( root.firstChild ){
        root.removeChild( root.firstChild );
    }

    if (windowWidth < 800) {
        divSummary.style.marginTop = "10px";
        divTitle.style.marginTop = "35px";

        root.appendChild(createLink("../../Portfolio/GameTrialVersion",
         "../images/Videogame.png", "../images/VideogameHover.png", 33, 23, "portfolioBox", false, "Game"));

        root.appendChild(createLink("../../Portfolio/Calculator/calc.html", "../images/Calculator.png",
        "../images/CalculatorHover.png", 30, 38, "portfolioBox", false));

        root.appendChild(createLink("../../Portfolio/Pen Website/pen.html", "../images/Website.png",
        "../images/Website Hover.png", 23, 23, "portfolioBox", false, "Website"));
    }
    else {
        divSummary.style.marginTop = "65px";

        root.appendChild(createLink("#", "../images/Videogame.png", 
    "../images/VideogameHover.png", 45, 30, "portfolioBox", false, "Game"));

    root.appendChild(createLink("../../Portfolio/Calculator/calc.html", "../images/Calculator.png",
    "../images/CalculatorHover.png", 40, 50, "portfolioBox", false));

    root.appendChild(createLink("../../Portfolio/Pen Website/pen.html", "../images/Website.png",
    "../images/Website Hover.png", 30, 30, "portfolioBox", false, "Website"));
    }

    let portfolioText = "As a first-year student at SAIT, I'm still learning all that I can about programming."
    + " However, I love to learn and I plan on picking up as many skills as I can."
    + " Here's a basic calculator app (built in JavaScript), along with a playable RPG game (built in JavaFX)."
    + " I've also built this website, and provided a link to a sample company site.";
    document.getElementById("summary").innerHTML = portfolioText;
}

function loadResumeSummary() {
    clearSummaryImage();
    resetSummary();
    resetTitle();

    let resumeSummary = "Software Development Student at SAIT with leadership experience, web development knowledge"
    + " and general programming skills. I've completed this website along with a sample company site (working on two others)"
    + " and a videogame written in Java. I have a passion for programming and I'm looking to further my skills"
    + " in an internship position.";
    document.getElementById("summary").innerHTML = resumeSummary;
    
    let root = document.getElementById("skills-list");
    while( root.firstChild ){
        root.removeChild( root.firstChild );
    }

    if (windowWidth < 800) {
        divSummary.style.marginTop = "30px";
        divTitle.style.marginTop = "50px";

        /*Creating Resume icon and adding to ul*/
        root.appendChild(createLink("../../Portfolio/Resume.pdf", "../images/ResumeDownload.png",
        "../images/ResumeHover.png", 30, 30, "resumeBox", true, "Resume"));

        /*CV currently removed due to irrelevant work experience*/
        /*Creating CV icon and adding to ul*/
        /*root.appendChild(createLink("../../Portfolio/Resume.pdf", "../images/CVDownload.png", 
        "../images/CVHover.png", 40, 45, "resumeBox", true));*/
    }
    else {
        divSummary.style.marginTop = "65px";

        /*Creating Resume icon and adding to ul*/
        root.appendChild(createLink("../../Portfolio/Resume.pdf", "../images/ResumeDownload.png",
        "../images/ResumeHover.png", 32, 32, "resumeBox", true, "Resume"));

        /*CV currently removed due to irrelevant work experience*/
        /*Creating CV icon and adding to ul*/
        /*root.appendChild(createLink("../../Portfolio/Resume.pdf", "../images/CVDownload.png", 
        "../images/CVHover.png", 45, 55, "resumeBox", true));*/
    }
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
        iconContainer.setAttribute('download',text);
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
    
    if (windowWidth > 800) {
        iconDownload.onmouseenter = function() {iconContainer.removeChild(iconContainer.firstChild); 
            iconContainer.style.color = "rgb(111, 160, 233)"; iconContainer.prepend(hoverImage)};
        iconDownload.onmouseleave = function() {iconContainer.removeChild(iconContainer.firstChild); 
            iconContainer.style.color = "black"; iconContainer.prepend(iconImage)};
    }
    iconContainer.onclick = function() {iconContainer.removeChild(iconContainer.firstChild); 
        iconContainer.style.color = "black"; iconContainer.prepend(iconImage)};
        
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
    divSummary.style.textAlign = "center";
    if (windowWidth < 800) {
        divSummary.style.marginTop = "70px";
        divSummary.style.width = "75%";
    }
    else {
        divSummary.style.marginTop = "110px";
        divSummary.style.width = "50%";
    }
}

function resetTitle() {
    if (windowWidth < 800) {
        divTitle.style.marginTop = "70px";
    }
    else {
        divTitle.style.marginTop = "120px";
    }
}

function loadFooterHovers() {
    createFooterHover(document.getElementById("linkedIn"), "../images/LinkedIn.png", "../images/LinkedInHover.png");
    createFooterHover(document.getElementById("Indeed"), "../images/Indeed.png", "../images/IndeedHover.png");
    createFooterHover(document.getElementById("StackOverflow"), "../images/StackOverflow.png", 
    "../images/StackOverflowHover.png");
    createFooterHover(document.getElementById("Github"), "../images/Github.png", "../images/GithubHover.png");
}

function createFooterHover(item, src, hoverSrc) {
    const iconImage = document.createElement("img");
    iconImage.src = src;
    iconImage.classList.add("imgMedia");

    const hoverImage = document.createElement("img");
    hoverImage.src = hoverSrc;
    hoverImage.classList.add("imgMedia");

    item.onmouseover = function() {item.src = hoverSrc;}
    item.onmouseout = function() {item.src = src;}
}