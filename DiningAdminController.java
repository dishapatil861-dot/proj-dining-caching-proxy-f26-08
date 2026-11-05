package edu.ucsb.cs156.diningcachingproxy.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import edu.ucsb.cs156.diningcachingproxy.services.DiningProxyService;

@Tag(name = "Dining Admin Endpoints")
@RestController
@RequestMapping("/api/admin/endpoints")
public class DiningAdminController {

    @Autowired
    private DiningProxyService diningProxyService;

    @Operation(summary = "Force refresh a single cached endpoint on demand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshEndpoint(@RequestParam String path) {
        try {
            // Service madhla cache clear/refresh karnyacha direct call
            diningProxyService.forceRefreshCache(path);
            return ResponseEntity.ok("Cache refreshed successfully for path: " + path);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
