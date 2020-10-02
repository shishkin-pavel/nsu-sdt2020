(ns lab1.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn my-map [f data]
  (reduce #(conj %1 (f %2))
          []
          data))

(defn my-filter [f data]
  (reduce (fn [a b]
            (do
              (println "a" a)
              (println "b" b)
              (if (f b)
                  (conj a b)
                  a)))
          []
          data))

;;(println (my-map #(* %1 %1) '(2 4 3)))
;;(println (my-map even? '(2 4 3)))
;;(println "my-filter")
;;(println (my-filter even? (range 1 10)))

;console output
;my-filter
;%1 []
;%2 1
; Syntax error (ArityException) compiling at (c:\home\koshi8bit\lab1\src\lab1\core.clj:28:1).
; Wrong number of args (0) passed to: clojure.lang.PersistentVector
; Evaluation of file core.clj failed: class clojure.lang.Compiler$CompilerException

(defn in?
  [coll elm]
  (some #(= elm %) coll))
  ; (not (nil? (some #(= elm %) coll))))

(defn f
  [data]
  (println data))

(defn f2
  [data]
  (if (empty? data)
      '()
      (do
        (f (first data)
        (f2 (rest data))
      ))))



(f2 '(1 4 3))
