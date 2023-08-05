{
    let x = 8;
    {
        let y = x + 5;
    }
}

myFun();

function myFun() {
    let x = 8;
    myFun2();
    function myFun2() {
        //Here:- Closure (myFun) x : 8
        let y = x + 5;
    }
}

//The self-invoking function only runs once. It sets the counter to zero (0), and returns a function expression.
const myCounter = (function(){
let counter = 0;
return function(){
    //Closure counter: 0
    counter += 1;
    return counter;
}
}());

//When we call myCounter() the selft invoked funtion will executes only once
//and will returnanother funtion with its lex scope or with clusere of SIF.
myCounter();
myCounter();
const cnt = myCounter();//3

//IIFE or SIF automatically executes without calling

(function sif(){
var h = 43;
})();


//Here's a solid example of how a self invoking anonymous function could be useful.

for( var i = 0; i < 10; i++ ) {
  setTimeout(function(){
    console.log(i)
  })
}
//Output: 10, 10, 10, 10, 10...

for( var i = 0; i < 10; i++ ) {
  (function(num){
    setTimeout(function(){
        //Closure i : 0
      console.log(num)
    })
  })(i)
}
//Output: 0, 1, 2, 3, 4...

/*

Short answer is : to prevent pollution of the Global (or higher) scope.

IIFE (Immediately Invoked Function Expressions) is the best practice for writing scripts as plug-ins, add-ons, user scripts or whatever scripts are expected to work with other people's scripts. This ensures that any variable you define does not give undesired effects on other scripts.

This is the other way to write IIFE expression. I personally prefer this following method:

void function() {
  console.log('boo!');
  // expected output: "boo!"
}();

*/
console.log('-----------------------------------------')
for (let i = 0; i < 5; i++) {
    
    const callback = () => {
        console.log(i)
    }
    setTimeout(callback, 3000)
    console.dir(callback, i)
}