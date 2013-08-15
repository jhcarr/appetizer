(defproject appetizer "0.1.0-SNAPSHOT"
  :description "Appetizer the Dujour Dashboard"
  :url "http://github.com/"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 ;; json 
                 [cheshire "5.2.0"]
                 ;; Routing library
                 [compojure "1.1.5"]
                 ;; Templating for Cojure
                 [enlive "1.1.1"]
                 ;; HTML in Clojure
                 [hiccup "1.0.4"]
                 ;; HTML->Hiccup
                 [hickory "0.4.1"]
                 ;; Database connectivity
                 [org.clojure/java.jdbc "0.3.0-alpha4"]
                 [postgresql/postgresql "9.1-901.jdbc4"]
                 [org.hsqldb/hsqldb "2.2.9"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler appetizer.controllers.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
