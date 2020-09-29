function loadPortfolio() {
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
    let resumeSummary = "Here is a basic summary of my resume and stuff";
    document.getElementById("summary-text").innerHTML = resumeSummary;
    document.getElementById("summary").style.height = "25vh";
    
    let root = document.getElementById("skills-list");
    while( root.firstChild ){
        root.removeChild( root.firstChild );
    }

    /*Creating Resume icon and adding to ul*/
    root.appendChild(createDownload("../../Portfolio/Resume.pdf", "../images/ResumeDownload.png",
    "../images/ResumeHover.png", 30, 30, "download-box", "Resume"));

    /*Creating CV icon and adding to ul*/
    root.appendChild(createDownload("../../Portfolio/Resume.pdf", "../images/CVDownload.png", 
    "../images/CVHover.png", 40, 46, "download-box"));
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

function createDownload(downloadSrc, imgSrc, hoverSrc, width, height, boxType, text) {
    const iconDownload = document.createElement("li");
    iconDownload.classList.add(boxType);

    /*Creating div container and adding to li*/
    const iconContainer = document.createElement("a");
    iconContainer.classList.add("skills");
    iconContainer.classList.add("remove-text-decoration");
    iconContainer.href = downloadSrc;
    iconContainer.setAttribute('download',"download");
    iconDownload.appendChild(iconContainer);

    /*Creating image element and adding to div container*/
    const iconImage = createImage(imgSrc, width, height);
    if (text != undefined) {
        iconContainer.textContent = text;
    }
    
    iconContainer.onmouseover = function() {hoverImage(iconImage, hoverSrc)};
    iconContainer.onmouseout = function() {iconImage.src = imgSrc};
    iconContainer.prepend(iconImage);

    return iconDownload;
}

function hoverImage(iconImage, hoverSrc) {
    iconImage.src = hoverSrc;
}


function createImage(imageSource, width, height) {
    const image = document.createElement("img");
    image.src = imageSource;
    image.width = width;
    image.height = height;
    return image;
}

function loadHomePage() {
    location.reload();
return false;
}