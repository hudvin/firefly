package firefly

import grails.converters.JSON
import org.springframework.web.multipart.MultipartHttpServletRequest

class UploaderController {

    FileService fileService

    def upload = {
        Collection result = []
//        Binary binary
        MultipartHttpServletRequest mpr = (MultipartHttpServletRequest) request;
        mpr.getFileNames().each {
          def file =   fileService.saveFile(mpr.getFile(it))
            //binary = Binary.storeMyFileMethod(mpr.getFile(it))
            result << [name: file.filename, size: file.length]
        }

        def responseData = [
                'files': result
        ]
        println(responseData)
        render responseData as JSON


//        def t=  render(contentType: "text/json") {
//            files = [
//                    result
//            ]
//        }
//
//        println(t)
//        t


//
//        def t =  result as JSON
//        println(t)
//        render t
    }

    def index() {}
}
