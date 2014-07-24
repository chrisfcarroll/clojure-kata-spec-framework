(ns testfwk.core-test
  (:require 
            [testfwk.core :refer :all]))

  (ns testfwk.core-test.bootstrap (:require [testfwk.core :refer :all]))
 
    (def bootstrap-assertion 
      '(let  [output  (with-out-str (spec "A dummy spec" (print "I was run")))]
         (assert (= output "I was run"))
         true))
    (if (eval bootstrap-assertion)
      (println "Bootstrapped OK.")
      (throw (AssertionError. "Bootstrap assertion failed. spec failed to run its body.")))
    ;--end bootstrap--

(ns testfwk.core-test)

(spec "A Spec describes expected behaviour")

(spec "A Spec runs its body" (eval bootstrap-assertion))


(defn fake-fn-being-specced [] 
  (let [s "I was run"]  
    (print s) 
    s))
