/* 
JavaScript Hoisting:-

Moving all declaration of functions, variables or classes to the top of their scope, prior to execution of the code. 
Hoisting allows functions to be safely used in code before they are declared.
*/

// i.e. a variable can be declared after it has been used.
//or  a variable can be used before it has been declared.

x = 5;
console.log(x);//5
var x;

//console.log(i);//Uncaught ReferenceError: i is not defined bcz hoisted with their scope and it has block scope.
{
    //console.log(i);//Uncaught ReferenceError: Cannot access 'i' before initialization
    let i;   
}
/*

Using a let variable before it is declared will result in a ReferenceError.

*/

//Variables defined with let and const are hoisted to the top of the block, but not initialized.
//Meaning: The block of code is aware of the variable, but it cannot be used until it has been declared.
//The variable y is in a "temporal dead zone" from the start of the block until it is declared:

//y = 6;//Uncaught ReferenceError: Cannot access 'y' before initialization
//console.log(y);
let y;
//const z;//'const' declarations must be initialized.

//JavaScript Initializations are Not Hoisted
//JavaScript only hoists declarations, not initializations.
console.log(x2);//undefined not 10
var x2 = 10;


{
    let a = 1;
    const b = 2;
    var c = 3;
}
console.log(c);// c = 3 and a & b are not define bcz scope ended at this line.