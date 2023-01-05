package com.axis.springbootdemo.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.axis.springbootdemo.entity.Cricketer;

@RestController
public class CricketerController {
	private static ArrayList<Cricketer> crickList;
	static {
		crickList=new ArrayList<>();
		crickList.add(new Cricketer(1001, "Surya Kumar", 101, 51, 8, 6, 217.2));
		crickList.add(new Cricketer(1002, "Virat Kohali", 80, 60, 2, 4, 120.5));
		crickList.add(new Cricketer(1005, "Ishan Kishan", 36, 45, 1, 1, 87.6));
		crickList.add(new Cricketer(1004, "KL Rahul", 25, 55, 1, 0, 42));
		crickList.add(new Cricketer(1003, "Rohit Sharma", 34, 34, 2, 2, 100));
	}
	
	@GetMapping("/message")
	public String getMessage() {
		return "Hello...First Springboot project\n Good Afternoon.";
	}
	
	@GetMapping("/cricketers")
	public ArrayList<Cricketer> getCricketer() {
		return crickList;
	}
	
//	Get cricketer by ID
	@GetMapping("/cricketers/{cricketerId}")
	public Cricketer getCricketerById(@PathVariable int cricketerId) {
		for(Cricketer ck:crickList) {
			if(ck.getCricketerId()==cricketerId) {
				return ck;
			}
		}
		return null;
	}
	
	@PostMapping("/cricketer")
	public ResponseEntity<String> addCricketer(@RequestBody Cricketer cricketer) {
		crickList.add(cricketer);
		return new ResponseEntity<>("Cricketer added successfully...",HttpStatus.OK);
	}
	
	//Update request
	@PutMapping("/cricketer/update/{cricketerId}")
	public ResponseEntity<String> updateCricketer(@PathVariable int cricketerId, @RequestBody Cricketer updatedCricketer){
		if(cricketerId!=updatedCricketer.getCricketerId()) {
			return new ResponseEntity<String>("Cricketer id's do not match!!!",HttpStatus.BAD_REQUEST);
		}
		int index=crickList.indexOf(updatedCricketer);
		if(index==-1) {
			return new ResponseEntity<String>("Cricketer Data updated Successfully !!!",HttpStatus.OK);
		}else {
			crickList.get(index).setBalls(updatedCricketer.getBalls());
			crickList.get(index).setRunsScored(updatedCricketer.getRunsScored());
	        crickList.get(index).setSixes(updatedCricketer.getSixes());
	        crickList.get(index).setStrikeRate(updatedCricketer.getStrikeRate());
	            return new ResponseEntity<String>("Cricketer data updated successfully...",HttpStatus.OK);
		}
	}
	
	  @DeleteMapping("/cricketer/delete/{cricketerId}")
	    public ResponseEntity<String> deleteCricketer(@PathVariable int cricketerId) {
	        Cricketer cricketer = getCricketerById(cricketerId);
	        if(cricketer == null) {
	            return new ResponseEntity<String>("Cricketer with id "+cricketerId+" is not found!!!",HttpStatus.NOT_FOUND);
	        }else {
	            crickList.remove(cricketer);
	            return new ResponseEntity<String>("Cricketer with id "+cricketerId+" deleted Successfully!!!",HttpStatus.OK);
	        }
	    }
}
