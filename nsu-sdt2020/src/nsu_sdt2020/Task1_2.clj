(ns Task1_2)

(defn addLetter
  ([elements res] (addLetter elements res '()))
  ([elements res acc]
   (if (> (count elements) 0)
     (if (.equals (first elements) (first res))
       (recur (rest elements) res acc)
       (recur (rest elements) res (concat acc (list (cons (first elements) res)) ))
       )
     acc )))

(defn buildWord
  ([elements result] (buildWord elements result '()))
  ([elements result acc]
   (if (> (count result) 0)
     (recur elements (rest result) (concat acc (addLetter elements (first result))))
     acc)
   ))

(defn task1
  ([elements constLen] (task1 elements constLen 1 '(())))
  ([elements constLen len acc]
   (if
     (> (- constLen len) 0)
     (recur elements constLen (inc len) (buildWord elements acc))
     (buildWord elements acc)
     )))

(defn -main
  [& args]
  (println (task1 '(:a :b :c) 3))
  (println (task1 '(a b c) 3))
  (println (task1 '((a) (b) (c)) 3))  )


