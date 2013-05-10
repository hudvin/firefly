class UrlMappings {



	static mappings = {
		"/$controller/$action?/$id?"{
            constraints {
				// apply constraints here
			}

		}

        //static/pdfjs/web/locale/en-US/viewer.properties to /pdfjs/web/locale/en-US/viewer.properties

        "/static/pdfjs/web/locale/en-US/viewer.properties"
                {
                    controller = "redirect"
                    action = "goTo"
                    urlToGo = "/pdfjs/web/locale/en-US/viewer.properties"
                }


        "/build/pdf.js"
                {
                    controller = "redirect"
                    action = "goTo"
                    urlToGo = "/pdfjs/build/pdf.js"
                }
        "/"(view:"/index")
		"500"(view:'/error')
    }
}
