(let [f #(if (= % 1) 1 (* % (f (dec %))))]
  (f 2))

(letfn [(f [x] (if (= x 1) 1 (* x (f (dec x)))))]
  (f 4))