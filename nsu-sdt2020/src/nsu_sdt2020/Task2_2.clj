(ns Task2_2)

(def ^:const step 0.01)

(defn count-trapeze [f start end]
  (do
    ;(println "From" start "; To " end "=>"  (* (/ (+ (f start) (f end)) 2) (- end start)))
    (* (/ (+ (f start) (f end)) 2) (- end start))
  ))

(defn create-integral-seq [f]
  (iterate #(let [acc (first %) n (second %)]
              [(+ acc (count-trapeze f (* step n) (* step (inc n)))) (inc n)]
              )
           [0 0])
  )

(defn integral [f]
  (let [integral-seq (create-integral-seq f)]
    (fn [x]
      (+ (first (nth integral-seq (int (/ x step))))
         (count-trapeze f (* step (int (/ x step))) x)))
    )
  )

(defn delta [a b]
  (if (>= a b)
    (- a b)
    (- b a)))

(defn print-result [a b]
  (do
    (print "      Expected:" a ".   Actual:" b )
    (if (<= (delta a b) 0.01) (println ".    =>  Correct.") (println ".   => Incorrect.")))
  )

(defn -main
  [& args]

  (let [x 1]      (time(print-result 1 ((integral (fn [x] (* x 2))) x))))
  (let [x 1]      (time(print-result 1 ((integral (fn [x] (* x 2))) x)))) ; =>  прирост производительности
  (let [x 3]      (print-result 9 ((integral (fn [x] (* x 2))) x)))
  (let [x 10]     (print-result 100 ((integral (fn [x] (* x 2))) x)))

  (let [x 1]      (print-result (/ 1.0 3) ((integral (fn [x] (* x x))) x)))
  (let [x 3]      (print-result 9 ((integral (fn [x] (* x x))) x)))
  (let [x 10]     (print-result (/ 1000.0 3) ((integral (fn [x] (* x x))) x)))

  (let [x 1]      (print-result 5.5 ((integral (fn [x] (+ x 5))) x)))
  (let [x 3]      (print-result 19.5 ((integral (fn [x] (+ x 5))) x)))
  (let [x 10]     (print-result 100 ((integral (fn [x] (+ x 5))) x)))

)
