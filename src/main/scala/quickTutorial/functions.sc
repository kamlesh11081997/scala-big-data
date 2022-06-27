def squareIt(x:Int):Int={
  x*x
}

def cubeIt(x:Int):Int={
  x*x*x
}

println(squareIt(2))
println(cubeIt(3))

//HOF : Higher order function
def transformIt(x:Int,f:Int=>Int):Int={
  f(x)
}

// passing function as parameter in HOF
val cube2=transformIt(2,cubeIt)
println(cube2)
val square2=transformIt(2,squareIt)
println(square2)


// syntactic sugar of calling a HOF
val resultanonymousfunc=transformIt(2,x=>x*x*x*x)
println(resultanonymousfunc)
transformIt(10,x=>x/2)
transformIt(2,x=>{val y=x*2; y*y })