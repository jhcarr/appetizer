(ns appetizer.handler
  (:use compojure.core
        [net.cgrand.enlive-html])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [appetizer.views.layout :as bar]
            ))

(def layout (html-resource "appetizer/views/layout.html"))
(def foo (bar/template))
(defroutes app-routes
  (GET "/" [] ;;(emit* layout)
       foo)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
