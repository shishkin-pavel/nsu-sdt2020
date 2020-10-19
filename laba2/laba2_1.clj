(ns laba2-1.core
  (:gen-class))


(defn mini [fun x0 x1 dx]
  (* (* (+ (fun x0) (fun x1)) 0.5) dx))


(defn integral [fun x1 dx]
  (let [x0 (- x1 dx)]
   (if (not= x1 0) (+ (mini fun x0 x1 dx) (integral fun x0 dx))
    (mini fun x0 x1 dx))
    )
  )

(def integral-m (memoize integral))

(def f #(* % %))
(defn -main []
  (time (integral f 1000 1))
  (println "---------1")
<<<<<<< HEAD
  (time (integral-m f 1000 1))
  (time (integral-m f 1000 1))
  (time (integral-m f 1200 1)))
(-main)
=======
  (time (integral-m f 100 1))
  (time (integral f 100 1))
  (println "--------2")
  (time (integral f 120 1))
  (time (integral f 150 1)))
(-main)
>>>>>>> d55767b9dcb9e0d23598cce7886f52aa02ca742c
