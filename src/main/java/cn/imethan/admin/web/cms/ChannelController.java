package cn.imethan.admin.web.cms;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import cn.imethan.admin.document.cms.Channel;
import cn.imethan.admin.service.cms.ChannelService;
import cn.imethan.common.mongodb.SearchFilter;

/**
 * ChannelController.java
 *
 * @author Ethan Wong
 * @time 2014年3月8日下午1:15:59
 */
@Controller
@RequestMapping("/console/cms/channel")
public class ChannelController {

	@Autowired
	private ChannelService channelService;

	private int defaultPageNo = 0;
	private int defaultPageSize = 10;

	@RequestMapping(value = "/{pageNo}/{pageSize}", method = { RequestMethod.GET })
	@ResponseBody
	public Page<Channel> list(Model model, ServletRequest request, @PathVariable int pageNo, @PathVariable int pageSize) {
		// key:"Operator"_"field",value:values
		Map<String, Object> parameters = WebUtils.getParametersStartingWith(request, SearchFilter.prefix);

		pageNo = pageNo >= 0 ? pageNo : defaultPageNo;
		pageSize = pageSize > 0 ? pageSize : defaultPageSize;

		return channelService.getPage(parameters, new PageRequest(pageNo, pageSize, Direction.DESC, "createTime"));
	}

	@RequestMapping
	public String page(Model model,ServletRequest request, Pageable pageable) {
		Map<String, Object> parameters = WebUtils.getParametersStartingWith(request, SearchFilter.prefix);

		model.addAttribute("users",channelService.getPage(parameters, pageable));
		
		return "";
	}

}
