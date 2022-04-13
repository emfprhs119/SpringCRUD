package com.min.mutabledata;

import com.min.mutabledata.model.MockEntity;
import com.min.mutabledata.model.MockService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/mock")
public class MockApiController {

    @Autowired
    private MockService mockService;

    /**
     * 모든 mock 데이터를 가져오는 API
     * @return response(status, mock_list)
     */
    @Operation(summary = "Get All Data", description = "모든 mock 데이터를 가져오는 API")
    @GetMapping("")
    public ResponseEntity<List<MockEntity>> getAll() {
        List<MockEntity> userList = mockService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    /**
     * 특정 mock 데이터를 가져오는 API
     * @param id ID (PK)
     * @return response(status, mock)
     */
    @Operation(summary = "Get Data by Id", description = "특정 mock 데이터를 가져오는 API")
    @GetMapping("/{id}")
    public ResponseEntity<MockEntity> get(@PathVariable("id") long id) {
        Optional<MockEntity> MockEntity = mockService.get(id);
        if (MockEntity.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(MockEntity.get());
        }
    }

    /**
     * mock 추가 API
     * @param mockEntity 추가할 데이터
     * @return response(status, mock)
     */
    @Operation(summary = "Insert Data", description = "mock 데이터를 추가하는 API")
    @PostMapping("")
    public ResponseEntity<MockEntity> create(@RequestBody MockEntity mockEntity) {
        MockEntity user = mockService.insert(mockEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    /**
     * mock 데이터 수정 API
     * @param id ID (PK)
     * @param mockEntity 수정할 데이터
     * @return response(status, mock)
     */
    @Operation(summary = "Update Data use Id", description = "특정 mock 데이터를 전체 변경하는 API")
    @PutMapping("/{id}")
    public ResponseEntity<MockEntity> update(@PathVariable("id") long id, @RequestBody MockEntity mockEntity) {
        MockEntity mockResult = mockService.update(id,mockEntity);
        return ResponseEntity.status(HttpStatus.OK).body(mockResult);
    }

    /**
     * mock 데이터 패치 API
     * @param id ID (PK)
     * @param mockEntity 수정할 데이터
     * @return response(status, mock)
     */
    @Operation(summary = "Patch Data use Id", description = "특정 mock 데이터를 일부 변경하는 API")
    @PatchMapping("/{id}")
    public ResponseEntity<MockEntity> modify(@PathVariable("id") long id, @RequestBody MockEntity mockEntity) {
        MockEntity mockResult = mockService.patch(id,mockEntity);
        return ResponseEntity.status(HttpStatus.OK).body(mockResult);
    }

    /**
     * mock 제거 API
     * @param id ID (PK)
     * @return response(status)
     */
    @Operation(summary = "Delete Data use Id", description = "특정 mock 데이터를 제거하는 API")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        mockService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
