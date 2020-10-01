function loadAboutMe() {
    clearSummaryImage();

    const aboutMeText = "This is some basic information about me. Included, but not limited to: "
    + "where I was born, when I moved to California, my interests, maybe a job summary, "
    + "and my schooling.";
    document.getElementById("summary-text").innerHTML = aboutMeText;

    let divSummary = document.getElementById("summary");
    divSummary.style.height = "21.5vh";

    const imageContainer = document.createElement("div");
    divSummary.prepend(imageContainer);
    const image = createImage("../images/Profile Picture.jpg", 150, 150);
    image.style.marginRight = "5vh";
    imageContainer.append(image);

    let root = document.getElementById("skills-list");
    while( root.firstChild ){
        root.removeChild( root.firstChild );
    }
}

function loadPortfolio() {
    clearSummaryImage();

    let portfolioText = "This is information about my portfolio, and the various" 
    + " projects that I have worked on throughout my career";
    document.getElementById("summary-text").innerHTML = portfolioText;
    document.getElementById("summary").style.height = "15vh";
    
    let root = document.getElementById("skills-list");
    while( root.firstChild ){
        root.removeChild( root.firstChild );
    }

    root.appendChild(createIcon("../images/Videogame.png", 40, 30, "download-box", "Game"));
}

function loadResumeSummary() {
    clearSummaryImage();

    let resumeSummary = "Here is a basic summary of my resume and stuff";
    document.getElementById("summary-text").innerHTML = resumeSummary;
    document.getElementById("summary").style.height = "25vh";
    
    let root = document.getElementById("skills-list");
    while( root.firstChild ){
        root.removeChild( root.firstChild );
    }

    /*Creating Resume icon and adding to ul*/
    root.appendChild(createLink("../../Portfolio/Resume.pdf", "../images/ResumeDownload.png",
    "../images/ResumeHover.png", 30, 30, "download-box", true, "Resume"));

    /*Creating CV icon and adding to ul*/
    root.appendChild(createLink("../../Portfolio/Resume.pdf", "../images/CVDownload.png", 
    "../images/CVHover.png", 40, 46, "download-box", true));
}

function createIcon(src, width, height, boxType, text) {
    const iconDownload = document.createElement("li");
    iconDownload.classList.add(boxType);

    /*Creating div container and adding to li*/
    const iconContainer = document.createElement("div");
    iconContainer.classList.add("skills");
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
    iconContainer.classList.add("skills");
    iconContainer.classList.add("remove-text-decoration");
    iconContainer.href = link;
    if (isDownload==true){
        iconContainer.setAttribute('download',"download");
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