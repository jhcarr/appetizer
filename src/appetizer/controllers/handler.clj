(ns appetizer.controllers.handler
  (:use compojure.core
        [net.cgrand.enlive-html]
        [appetizer.views.overview :only (make-overview)]
        [appetizer.views.index :only (make-index)]
        [appetizer.views.demograph :only (make-demograph)]
        [appetizer.views.dc_experiment :only (make-dc-experiment)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [appetizer.views.layout :as layout]))

(defn enlive-layout
  ;; Correct syntax for ampersand?
  [body]
  (->> body
       (html)
       (content)
       (at (html-resource "appetizer/views/layout.html") [:#main])
       (emit*)))

;;Enlive
(defroutes app-routes
  (GET "/" [] (make-index))
  (GET "/overview" [] (make-overview))
  (GET "/demograph" {} (make-demograph))
  (GET "/dc_experiment" {} (make-dc-experiment))  
  (GET "/about" [] (enlive-layout "FIX ME"))
  (GET "/contact" [] (enlive-layout "MORE FIX ME"))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
