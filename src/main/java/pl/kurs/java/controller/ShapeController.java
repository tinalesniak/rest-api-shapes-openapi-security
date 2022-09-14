package pl.kurs.java.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.kurs.java.model.command.CreateShapeCommand;
import pl.kurs.java.model.inheritance.Shape;
import pl.kurs.java.query.FindShapeQuery;
import pl.kurs.java.service.ShapeService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shapes")
public class ShapeController {

    private final ShapeService shapeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity saveShape(@RequestBody @Valid CreateShapeCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shapeService.saveShape(command));
    }

    @GetMapping
    public ResponseEntity find(@ModelAttribute FindShapeQuery findShapeQuery) {
        return ResponseEntity.ok(shapeService.findShapes(findShapeQuery).stream()
                .collect(Collectors.toList()));
    }

//    @GetMapping
//    public List<Shape> searchShapes(
//            @RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
//            @RequestParam(required = false, value = "size", defaultValue = "20") Integer size) {
//        return shapeService.search(page, size);
//    }
}
