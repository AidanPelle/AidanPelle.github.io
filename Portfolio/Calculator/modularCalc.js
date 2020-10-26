const calculator = (() => {
    
    // Required variables
    let calc = {
        num1: "0",
        num2: null,
        currentFunction: null,
        output: document.getElementById("header-content")
    }

    // Sub functions to support major ones
    displayOne = () => calc.output.textContent = calc.num1;
    displayTwo = () => calc.output.textContent = calc.num1 + " " + calc.currentFunction.textContent;
    displayThree = () => calc.output.textContent = calc.num1 + " " + calc.currentFunction.textContent + " " + calc.num2;
    isSelected = () => calc.currentFunction.style.boxShadow;
    select = () => calc.currentFunction.style.boxShadow = "inset 0px 0px 0px 4px rgb(60, 60, 60)";
    clearSelection = () => {if (calc.currentFunction != null) 
        {calc.currentFunction.style.boxShadow = "none"; calc.currentFunction = null;}}
    hasDecimal = (aString) => aString.toString().indexOf(".") != -1;
    roundNum = (number) => Math.round(number * 1_000_000) / 1_000_000;
    storeNumber = (n, num) => {if (num == "." && !hasDecimal(calc[n])) addDecimal(n, num); else if (num != ".") addNumber(n, num);}
    addDecimal = (n, num) => {if (calc[n] == 0 || calc[n] == null) calc[n] = "0."; else calc[n] += num;}
    addNumber = (n, num) => {if (calc[n] == "0" || calc[n] == null) calc[n] = num; else calc[n] += num;}


    // Functions available to html doc
    clearAll = () => {calc.num1 = 0; calc.num2 = null; clearSelection(); displayOne();}
    selectFunction = (symbol) => {(calc.num2 != null ? calculate() : 
        false); clearSelection(); calc.currentFunction = symbol; select(); displayTwo();}

    flipSign = () => {if (calc.num2 != null) {calc.num2 = (calc.num2 * -1).toString(); displayThree();} 
    else if (calc.currentFunction == null){calc.num1 = (calc.num1 * -1).toString(); displayOne();}} 
    switchNum = (newNum) => {if (calc.currentFunction != null) {storeNumber("num2", newNum); displayThree();}
     else {storeNumber("num1", newNum); displayOne();}}

    clearSingle = () => {
        if (calc.num2 != null) {
            calc.num2 = null;
            displayTwo();
        }
        else {
            if (calc.currentFunction == null)
                calc.num1 = 0;
            clearSelection();
            displayOne();
        }
    }
    calculate = () => {
        if (calc.num2 != null) {
            calc.num1 = roundNum(eval(calc.num1 + " " + calc.currentFunction.textContent + " " + calc.num2));
            calc.num2 = null;
            clearSelection();
            displayOne();
        }
    }

    return {
        calculate,
        switchNum,
        flipSign,
        clearAll,
        clearSingle,
        selectFunction
    };
})();