(ns clojure1.core)

(defn add_alphabet_to_word [started alphabet comb]
  (if (empty? alphabet)
    comb
    (let [firs (first alphabet) first_in_started (first started)]
      (if (not= firs first_in_started)
        (recur started (rest alphabet) (cons (cons firs started) comb))
        (recur started (rest alphabet) comb)))))

(defn add_new_combo [started alphabet tail]
  (let [combinations (add_alphabet_to_word (first started) alphabet ())]
    (if (empty? (rest started))
      (concat tail combinations)
      (recur (rest started) alphabet (concat tail combinations)))))

(defn main_rec [started n alphabet]
  (if (= n 0)
    started
    (recur (add_new_combo started alphabet ()) (- n 1) alphabet)))

;; (defn create_started_list [alphabet tail]
;; (if (empty? (rest alphabet))
;;  (cons (list (first alphabet)) tail)
;;  (create_started_list (rest alphabet) (cons (list (first alphabet)) tail))) )

(defn resolution [n alphabet]
  (let [started (main_rec '() 1 alphabet)]
    (if (= n 0)
      started
      (main_rec started (- n 1) alphabet))))

(defn -main
  [& args]
  (println (resolution (Integer/parseInt (last args)) (drop-last args))))