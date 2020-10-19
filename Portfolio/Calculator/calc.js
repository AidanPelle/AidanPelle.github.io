var num1 = 0;
var num2 = null;
var currentFunction = null;
var headerContent = document.getElementById("header-content");

function math(symbol) {
    if (num2 == null && symbol.style.boxShadow) {
        deactivateFunction();
        currentFunction = null;
        symbol.style.boxShadow = " ";
        headerContent.textContent = num1;
    }
    else if (num2 == null) {
        deactivateFunction();
        currentFunction = symbol.textContent;
        symbol.style.boxShadow = "inset 0px 0px 0px 4px rgb(60, 60, 60)";
        headerContent.textContent = num1 + " " + currentFunction;
    }
    else {
        equals();
        currentFunction = symbol.textContent;
        symbol.style.boxShadow = "inset 0px 0px 0px 4px rgb(60, 60, 60)";
        headerContent.textContent = num1 + " " + currentFunction;
    }
}

function equals() {
    if (num2 != null) {
        if (num1.charAt(num1.length - 1) == '.'){
            num1 = num1.slice(0, -1);
        }
        if (num2.charAt(num2.length - 1) == '.'){
            num2 = num2.slice(0, -1);
        }
        num1 = Number(num1);
        num2 = Number(num2);
        
        switch (currentFunction) {
            case "+": {
                num1 += num2;
                break;
            }
            case "-": {
                num1 -= num2;
                break;
            }
            case "*": {
                num1 *= num2;
                break;
            }
            case "/": {
                num1 /= num2;
                break;
            }
            case "%": {
                num1 %= num2;
                break;
            }
        }
    
        num1 = round(num1);
        num2 = null;
        num1 = num1.toString();
        headerContent.textContent = num1;
        deactivateFunction();
    }
}

function storeNumber(number) {
    if (currentFunction == null) {
        if (number != '.') {
            if (num1 == '0') {
                num1 = number;
            }
            else {
                num1 += number;
            }
        }
        else if (hasDecimal(num1) == false) {
            if (num1 == '0' || num1 == null) {
                num1 = "0.";
            }
            else {
                num1 += number;
            }
        }
        headerContent.textContent = num1;
    }
    else {
        if (number != '.') {
            if (num2 == '0' || num2 == null) {
                num2 = number;
            }
            else {
                num2 += number;
            }
        }
        else if (hasDecimal(num2) == false) {
            if (num2 == '0' || num2 == null) {
                num2 = "0.";
            }
            else {
                num2 += number;
            }
        }
        headerContent.textContent = num1 + " " + currentFunction + " " + num2;
    }
}

function storeNumberPartTwo(currentNum, newNum) {
    if (newNum != '.') {
        if (currentNum == '0' || currentNum == null) {
            currentNum = newNum;
        }
        else {
            currentNum += newNum;
        }
    }
    else if (hasDecimal(currentNum) == false) {
        if (currentNum == '0' || currentNum == null) {
            currentFunction = "0.";
        }
        else {
            currentNum += newNum;
        }
    }
    console.log(num1);
}

function flipSign() {
    if (num2 != null) {
        num2 = (num2 * -1).toString();
        headerContent.textContent = num1 + " " + currentFunction + " " + num2;
    }
    else if (num1 != 0 && currentFunction == null) {
        num1 = (num1 * -1).toString();
        headerContent.textContent = num1
    }
}

function clearSingle() {
    if (num2 != null) {
        num2 = null;
        headerContent.textContent = num1 + " " + currentFunction;
    }
    else if (currentFunction != null) {
        deactivateFunction();
        headerContent.textContent = num1;
    }
    else {
        num1 = 0;
        headerContent.textContent = num1;
    }
}

function clearAll() {
    deactivateFunction();
    num1 = 0;
    num2 = null;
    currentFunction = null;
    headerContent.textContent = num1;
}

function deactivateFunction() {
    switch (currentFunction) {
        case "+": {
            document.getElementById("plus").style = "box-shadow: ";
            currentFunction = null;
            break;
        }
        case "-": {
            document.getElementById("minus").style = "box-shadow: ";
            currentFunction = null;
            break;
        }
        case "*": {
            document.getElementById("multiply").style = "box-shadow: ";
            currentFunction = null;
            break;
        }
        case "/": {
            document.getElementById("divide").style = "box-shadow: ";
            currentFunction = null;
            break;
        }
        case "%": {
            document.getElementById("modulus").style = "box-shadow: ";
            currentFunction = null;
            break;
        }
    }
}

function round(number) {
    number *= 1000000;
    number = Math.round(number);
    number /= 1000000;
    return number;
}

function hasDecimal(stringNumber) {
    if (stringNumber != null) {
        for (let i = 0; i < stringNumber.length; i++) {
            if (stringNumber.charAt(i) == '.')
                return true;
        }
    }
    
    return false;
}

