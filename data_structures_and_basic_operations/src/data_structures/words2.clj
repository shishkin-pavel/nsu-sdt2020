(ns data_structures.words2)

(defn add-letter-to-word
  [word letter]
  (if (not (= (.substring word
                          (- (.length word) 1))
              letter))
    (.concat word letter)))

(defn create-words-by-letter
  ([letter words] (create-words-by-letter letter words (list)))
  ([letter words acc]
   (if (> (count words) 0)
     (if (not (= (add-letter-to-word (first words) letter) nil))
       (recur letter (rest words) (cons (add-letter-to-word (first words) letter)
                                        acc))
       (recur letter (rest words) acc))
     acc)))

(defn search-by-index
  ([i alphabet words] (search-by-index i alphabet words (list)))
  ([i alphabet words acc]
   (if (< i (count alphabet))
     (recur (inc i) alphabet words (concat
                                     (create-words-by-letter (nth alphabet i) words)
                                     acc))
     acc)))

(defn one-two
  [n alphabet words]
  (if (> n (.length (first words)))
    (recur n alphabet (search-by-index 0 alphabet words))
    words)
  )

(defn all-words [n alphabet]
  (vec (one-two n alphabet alphabet)))
