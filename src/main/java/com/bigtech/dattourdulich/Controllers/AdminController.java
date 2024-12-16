// Controller
package com.bigtech.dattourdulich.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigtech.dattourdulich.models.Itinerary;
import com.bigtech.dattourdulich.models.UserEntity;
import com.bigtech.dattourdulich.models.itinerary_nodes;
import com.bigtech.dattourdulich.models.tour_guides;
import com.bigtech.dattourdulich.models.tour;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.bigtech.dattourdulich.repository.TourRepository;
import com.bigtech.dattourdulich.repository.itrNodeRepository;
import com.bigtech.dattourdulich.repository.GuideRepository;
import com.bigtech.dattourdulich.repository.ItineraryRepository;
	
	@Controller
	public class AdminController {
		
		private GuideRepository GuideRepository;
		private TourRepository TourRepository;
		private ItineraryRepository ItineraryRepository;
		private itrNodeRepository itrnodeRepository;
	   
		@Autowired
		public AdminController(com.bigtech.dattourdulich.repository.GuideRepository guideRepository,
				com.bigtech.dattourdulich.repository.TourRepository tourRepository,
				com.bigtech.dattourdulich.repository.ItineraryRepository itineraryRepository,
				itrNodeRepository itrnodeRepository) {
			super();
			GuideRepository = guideRepository;
			TourRepository = tourRepository;
			ItineraryRepository = itineraryRepository;
			this.itrnodeRepository = itrnodeRepository;
		}

		@GetMapping("/admin/showGuides")
	    public String listTourGuides(Model model) {
			model.addAttribute("guidess", GuideRepository.findAll());
	        return "auth/item1"; // Tên file HTML hiển thị danh sách
	    }

		@RequestMapping("/admin/createGuides")
		String showAddGuides(Model model) {
			tour_guides newGuides = new tour_guides();
			model.addAttribute("guides", newGuides); // Truyền danh sách vào Model
			return "auth/createGuide";
		}
		
		@RequestMapping("/admin/ProCreGuides")
		String processAddGuides(@Valid @ModelAttribute("guides") tour_guides guide, BindingResult bindingResult, Model model) {
//			String name = guide.getGuideName();
//			String contact = guide.getContactInfo();
//			int exp = guide.getExperienceYears();
//			tour_guides guide1 = new tour_guides(name, contact, exp);
			
			GuideRepository.save(guide);
			return "redirect:/admin/showGuides";
		}
		
		@RequestMapping("/admin/deleteGuides")
		String deleteGuides(@RequestParam("id") int id) {
			tour_guides guide = GuideRepository.findById(id).get();
			GuideRepository.delete(guide);
			return "redirect:/admin/showGuides";
		}
		
		@RequestMapping("/admin/updateGuides")
		String updateguides(@RequestParam("id") int id, Model model) {
			tour_guides guide = GuideRepository.findById(id).get();
			model.addAttribute("guides", guide);
			return "auth/createGuide";
		}
		
		 
		 
	    @GetMapping("/admin/showTours")
	    public String listTour(Model model) {
	    	model.addAttribute("tour", TourRepository.findAll());
	        return "auth/item2"; // Tên file HTML hiển thị danh sách
	    }

	    // Hiển thị form để tạo tour
	    @RequestMapping("/admin/createTour")
	    String showAddTour(Model model) {
	        tour newTour = new tour();
	        model.addAttribute("tour", newTour); // Truyền đối tượng tour mới vào Model
	        return "auth/createTour"; // Tên file HTML để tạo tour
	    }

	    // Xử lý thêm tour
	    @RequestMapping("/admin/ProCreTour")
	    String processAddTour(@Valid @ModelAttribute("tour") tour newTour, BindingResult bindingResult, Model model) {
	    	String tour = newTour.getTourName();
			String descript = newTour.getDescriptions();
			int time = newTour.getDuration();
			double pri = newTour.getPrice();
			tour addTour = new tour(tour, descript, time, pri);
	        TourRepository.save(addTour); // Lưu tour vào cơ sở dữ liệu
	        return "redirect:/admin/showTours"; // Chuyển hướng về danh sách các tour
	    }
		
		@RequestMapping("/admin/deleteTour/{id}")
		public String deleteTour(@PathVariable("id") int id) {
		    TourRepository.deleteById(id); // Xóa hướng dẫn viên theo ID
		    return "redirect:/admin/showTours"; // Chuyển hướng về danh sách
		}
		
		@GetMapping("/admin/editTour/{id}")
		public String showEditTourForm(@PathVariable Integer id, Model model) {
		    tour tour = TourRepository.findById(id).orElse(null);
		    if (tour == null) {
		        return "redirect:/admin/showTours";
		    }
		    model.addAttribute("tour", tour);
		    return "auth/editTour";
		}

		@PostMapping("/admin/updateTour")
		public String updateTour(@Valid @ModelAttribute("tour") tour tour, BindingResult bindingResult) {
		    if (bindingResult.hasErrors()) {
		        return "auth/editTour"; // Nếu có lỗi, quay lại form sửa
		    }
		    TourRepository.save(tour); // Lưu thông tin đã chỉnh sửa vào database
		    return "redirect:/admin/showTours"; // Chuyển hướng về danh sách
		}
		
	
	    // Hiển thị form thêm lịch trình
	    @GetMapping("/{tourId}/add")
	    public String showAddItineraryForm(@PathVariable int tourId, Model model) {
	        Itinerary itinerary = new Itinerary();
	        model.addAttribute("itinerary", itinerary);
	        model.addAttribute("tourId", tourId);
	        return "auth/addItinerary"; // File HTML thêm lịch trình
	    }

	    // Xử lý thêm lịch trình
//	    @PostMapping("/{tourId}/add")
//	    public String addItinerary(@PathVariable int tourId, @ModelAttribute Itinerary itinerary) {
//	        tour tour = TourRepository.findById(tourId).orElse(null);
//	        if (tour == null) {
//	            return "redirect:/admin/showTours";
//	        }
//	        itinerary.setTour(tour);
//	        ItineraryRepository.save(itinerary);
//	        return "redirect:/admin/itinerary/" + tourId;
//	    }
	    
	    @RequestMapping("/admin/viewTours")
	    public String viewTour(Model model) {
	    	model.addAttribute("tour", TourRepository.findAll());
	        return "auth/item3"; // Tên file HTML hiển thị danh sách
	    }
	
	    @RequestMapping("/admin/show")
	    public String showInfo() {
	        return "/auth/adLayout";	
	    }
	
	    @RequestMapping("/admin/show/item1")
	    public String item1() {
	        return "/auth/item1";
	    }
	
	    @RequestMapping("/admin/show/item2")
	    public String item2() {
	        return "/auth/item2";
	    }
	    
	    @RequestMapping("/admin/show/item3")
	    public String item3() {
	        return "/auth/item3";
	    }
	    
	    @RequestMapping("/admin/showitInerary")
	    String showitInerary(Model model) {
	    	model.addAttribute("itrs", ItineraryRepository.findAll());
	    	return "/auth/item3";
	    }
	    
	    @RequestMapping("/admin/tourofInerary")
	    String tourofInerary(Model model) {
	    	model.addAttribute("tour", TourRepository.findAll());
	    	return "/auth/addInerary";
	    }
	    
	    @Transactional
	    @RequestMapping("/admin/createInerary/")
	    String createInerary(@RequestParam("id") int id, Model model) {
	    	tour t = TourRepository.findById(id).get();
	    	System.out.println(t.getTourName());
	    	Itinerary itr = new Itinerary("a", 0, "a", t);
	    	ItineraryRepository.save(itr);
	    	model.addAttribute("itr", itr);
	    	return "/auth/createinerary";
	    }
	    
	    @RequestMapping("/admin/ProCreItinerary")
	    String ProCreItinerary(@Valid @ModelAttribute("itr") Itinerary iti, BindingResult bindingResult, Model model) {
	    	if (iti.getDay_num() ==  0 || iti.getDescriptions() == "" || iti.getItineraries_name() == "") {
	    		return "/auth/createinerary";
	    	}
	    	System.out.println(iti.getId());
	    	ItineraryRepository.save(iti);
	    	 return "redirect:/admin/crtItrNode?id=" + iti.getId();
	    }
	    
	    @RequestMapping("/admin/crtItrNode")
	    String crtItrNode(Model model, @RequestParam("id") int id) {
	    	Itinerary itr = ItineraryRepository.findById(id).get();
	    	itinerary_nodes itinode = new itinerary_nodes("a", "a", 0, itr);
	    	model.addAttribute("itinode", itinode);
	    	return "auth/creItrNode";
	    }
	    
	    @RequestMapping("/admin/ProCreItrNode")
	    String ProCreItrNode(@Valid @ModelAttribute("itinode") itinerary_nodes itinode, BindingResult bindingResult, Model model) {
	    	int itrid = itinode.getItinerary().getId();
	    	if (itrid == 0 ) {
	    		return "auth/creItrNode";
	    	}
	    	Itinerary newitr = ItineraryRepository.findById(itrid).get();
	    	itinerary_nodes newitinode = new itinerary_nodes(itinode.getLocation(), itinode.getTime_node(), itinode.getDay_of_tour(), newitr);
	    	System.out.println(newitinode.toString());
	    	itrnodeRepository.save(newitinode);
	    	return "redirect:/admin/showitInerary";
	    }
	    
		@GetMapping("/admin/itinerary/{itrid}")
	    public String showItineraries(@PathVariable int itrid, Model model) {
	        Itinerary itr = ItineraryRepository.findById(itrid).orElse(null);
	        if (itr == null) {
	        	 return "redirect:/admin/showitInerary"; // Quay lại nếu không tìm thấy tour
	        }
	        
	        List<itinerary_nodes> listnode = itrnodeRepository.findByItinerary(itr);
	        model.addAttribute("nodes", listnode);
	        model.addAttribute("itr", itr);
	        return "auth/detailIniterary"; // File HTML hiển thị danh sách lịch trình
	    }
		
		@RequestMapping("admin/updateIti")
		String updateIti(@RequestParam("id") int id, Model model) {
			Itinerary itir = ItineraryRepository.findById(id).get();
			model.addAttribute("itr", itir);
			return "/auth/createinerary";
		}
		
		@RequestMapping("/admin/deleteitr")
		String deleteitr(@RequestParam("id") int id) {
			Itinerary itir = ItineraryRepository.findById(id).get();
			List<itinerary_nodes> listnode = itrnodeRepository.findByItinerary(itir);
			listnode.forEach(node -> {
				itrnodeRepository.delete(node);
			});
			ItineraryRepository.delete(itir);
			return "redirect:/admin/showitInerary";
		}

	
	}
	
