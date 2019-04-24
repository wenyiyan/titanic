package com.ezops.assignments.titanic.controller;

import com.ezops.assignments.titanic.model.Passenger;
import com.ezops.assignments.titanic.repository.PassengerRepo;
import com.ezops.assignments.titanic.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yao.chen
 */

@Controller
@RequestMapping("/titanic/passengers")
public class TitanicPassengersController {

    static Logger logger = LoggerFactory.getLogger(TitanicPassengersController.class);

    @Autowired
    private PassengerRepo passengerRepo;

    /**
     * list Titanic passengers
     * <pre>RequestParam:
     *      <li>pagenum : current page num </li>
     *      <li>pagesize: the size of data</li>
     *      <li>sortdatafield : column name that will be sorted</>
     *      <li>sortorder : DESC or ASC or null</li>
     * </>
     * @return
     */

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<RestResponse>listPassengers(@RequestParam("pagenum") int pagenum,
                                                      @RequestParam("pagesize") int pagesize,
                                                      @RequestParam("sortdatafield") String sortdatafield,
                                                      @RequestParam("sortorder") String sortorder){
        logger.debug("/titanic/passengers/list ->pagenum{} ,pagesize{},sortdatafield{},asc{}",pagenum,pagesize,sortdatafield,sortorder);

        RestResponse response = new RestResponse<>(RestResponse.STATUS_FAIL);

        try{
            if(sortdatafield!=null) {

                //sortOrder ASC
                if(sortorder.equals("asc")){
                    Page<Passenger> data = passengerRepo.findAll(PageRequest.of(pagenum, pagesize, Sort.by(Sort.Direction.ASC,sortdatafield)));
                    response = new RestResponse<>(RestResponse.STATUS_SUCCESS, data);

                }
                //sortOrder DESC
                else if (sortorder.equals("desc")){
                    Page<Passenger> data = passengerRepo.findAll(PageRequest.of(pagenum, pagesize, Sort.by(Sort.Direction.DESC,sortdatafield)));
                    response = new RestResponse<>(RestResponse.STATUS_SUCCESS, data);
                }

                // remove sort case
                else{
                    Page<Passenger> data = passengerRepo.findAll(PageRequest.of(pagenum, pagesize));
                    response = new RestResponse<>(RestResponse.STATUS_SUCCESS, data);
                }


            }
        }
        catch (Exception e){
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
            response = new RestResponse<>(RestResponse.STATUS_FAIL,e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<RestResponse>(response,response.getError_code());
    }


}
