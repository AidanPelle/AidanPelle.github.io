const calculator = (() => {
    
    // Required variables
    let calc = {
        num1: "0",
        num2: null,
        currentFunction: null,
        output: document.getElementById("header-content")
    }

    // Sub functions to support major ones
    function displayOne() {calc.output.textContent = calc.num1;}
    function displayTwo() {calc.output.textContent = calc.num1 + " " + calc.currentFunction.textContent;}
    function displayThree() {calc.output.textContent = calc.num1 + " " + calc.currentFunction.textContent + " " + calc.num2;}
    function select() {calc.currentFunction.style.boxShadow = "inset 0px 0px 0px 4px rgb(60, 60, 60)";}
    function clearSelection() {if (calc.currentFunction != null) 
        {calc.currentFunction.style.boxShadow = "none"; calc.currentFunction = null;}}
    function hasDecimal(aString) {return aString.toString().indexOf(".") != -1;}
    function roundNum(number) {return Math.round(number * 1_000_000) / 1_000_000;}
    function storeNumber(n, num) {if (num == "." && !hasDecimal(calc[n])) addDecimal(n, num); 
        else if (num != ".") addNumber(n, num);}
    function addDecimal(n, num) {if (calc[n] == 0 || calc[n] == null) calc[n] = "0."; else calc[n] += num;}
    function  addNumber(n, num) {if (calc[n] == "0" || calc[n] == null) calc[n] = num; else calc[n] += num;}

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
            console.log("Num1: " + calc.num1 + "\nFunction: " + calc.currentFunction.textContent + "\nNum2: " + calc.num2);
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