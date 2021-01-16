(ns Task2_1)

(def ^:const step 0.1)
(def ^:const eps 0.1)

(defn count-trapeze [f start end]
  (*    (/ (+ (f start) (f end)) 2)      (- end start)))

(def integrate
  (memoize
    (fn [f n]
      (if (= 0 n)
        0
        (+ (integrate f (dec n))
           (count-trapeze f (* step (dec n)) (* step n))))))
  )

(defn integral [f]
  (fn [x] (+ (integrate f (int (/ x step))) (count-trapeze f (* step (int (/ x step))) x))))

(defn delta [a b]
  (if (>= a b)
    (- a b)
    (- b a)))

(defn print-result [a b]
  (do
    (print "     Expected:" a ".   Actual:" b )
    (if (<= (delta a b) eps) (println ".    =>  Correct.") (println ".   => Incorrect.")))
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