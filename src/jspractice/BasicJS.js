var x = 10;
var x = 10;

let y = 11;
//let y = 11;//Cannot redeclare block-scoped variable 
const z = 12;
//const z = 12;//Compile time error - Cannot redeclare block-scoped variable 

//const a = sum(4, 5);//Runtime error - Uncaught ReferenceError: Cannot access 'sum' before initialization

//var has Global Scope and let & const has Script Scope outside the block.

var t = 0;
let l = 0;
const c = 0;
//sum has SCript Scope
const sum = (x, y) => {
    //Local Scope & this is undefined here bcx it Anonymous function.
    return x + y;
}

const a = sum(4, 5);

const mulAns = mul(4, 5);

//mul has Global Scope
function mul(x, y) {
    //this is Window bcz we are this function from window scope
    var x = 50;//it will shadow local x here same for anonymous fun
    var t = 200;//will not change outside t same for anonymous fun
    l = 4;
    //c = 5;//Uncaught TypeError: Assignment to constant variable.
    return x*y;
}
console.log(t, c, l);//t remains same as outside, l will change;

{
    //this block will be executed in same anonymous call in call stack. So all will chnage
    var x = 50;
    var t = 200;
    //l = 100; //Uncaught ReferenceError: Cannot access 'l' before initialization
    let l = 50;

}

console.log(t, c);//200, c, 4