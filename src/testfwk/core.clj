(ns testfwk.core)

(def results (atom {}))

(defn start-run [] (swap! results (fn [a] {})))

(defmacro spec
  "Execute a specification and resultss failure"
  [name & more]
  `(let [result#  (if ~(empty? more) 
                    :manual  
                    (try 
                      ~(cons 'do more)
                      :pass 
                      (catch AssertionError e# (.getMessage e#))))]
        (swap! results assoc ~name result#)
        result#
    ))

(defn report-to-console [] 
  (let [fails (filter 
                #(and 
                   (instance? String (val %)) 
                   (not= (key %) "Spec with a failing body")
                )  
                @results
              )
        passes (count (filter #(= :pass (val %)) @results))
        manuals (count (filter #(= :manual (val %)) @results)) 
        summary {:passes passes :manuals manuals :fails (count fails)}
        ]
    (println summary)
    (if (not (empty? fails))
      (println "Fails were:" fails)
      )
  ))


(ns testfwk.core.bootstrap (:require [testfwk.core :refer :all]))
 
  (def bootstrap-assertion 
    '(let  [output  (with-out-str (spec "A dummy spec" (print "I was run")))]
       (assert (= output "I was run"))
       true))
  (if (eval bootstrap-assertion)
    (println "Bootstrapped OK.")
    (throw 
      (AssertionError. 
       "Bootstrap assertion failed. Don't believe anything I claim has passed.")))


(start-run)
