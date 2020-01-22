package com.thinktag.consistenthash.controller;

import com.thinktag.consistenthash.Circle;
import com.thinktag.consistenthash.Node;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsistentHashController {

    Circle circle = new Circle();

    @PostMapping("api/serverNode")
    void addServerNode(@RequestParam(value="ip") String[] ips) throws Exception {
        for(String ip: ips) {
            circle.addNode(ip);
        }
    }


    @GetMapping("api/serverNodes")
    @ResponseBody
    List<Node> getServerNode() throws Exception {
        return circle.getCircleNodes();
    }

    @GetMapping("api/serverForItem")
    @ResponseBody
    Node getServerNode(@RequestParam String dataItem) throws Exception {
        return circle.getNode(dataItem);
    }
}
