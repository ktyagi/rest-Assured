
Map m =[ james  :"silly boy",
         janny  :"crazy girl",
         jimmy  :"Funny man",
         georges:"massive fella" ]
sorted = m.sort { a, b -> a.value <=> b.value }
println sorted
sorted1 = m.sort { a , b -> println(a.value.toLowerCase() ) <=>  println(b.value.toLowerCase()) }
println sorted1