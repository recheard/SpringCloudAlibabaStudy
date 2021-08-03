package com.alibaba.openfeign.test.openfeign;

import com.alibaba.openfeign.test.api.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author recheard
 * @description:
 * @date 2021/8/216:12
 */
@FeignClient("member-service")
public interface MemberServiceFeign extends MemberService {

}
