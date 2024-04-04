package pro.julleon.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pro.julleon.app.entity.Client;
import pro.julleon.app.service.ClientSearchService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

//7.	Сделать АПИ поиска. Искать можно любого клиента. Должна быть фильтрация и пагинация/сортировка. Фильтры:
//a.	Если передана дата рождения, то фильтр записей, где дата рождения больше чем переданный в запросе.
//b.	Если передан телефон, то фильтр по 100% сходству.
//d.	Если передан email, то фильтр по 100% сходству.
//c.	Если передано ФИО, то фильтр по like форматом ‘{text-from-request-param}%’
@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class ClientSearchController {

    private final ClientSearchService clientSearchService;

    @PostMapping
    public ResponseEntity<?> searchClientsViaJpaAndJPQL(
            @RequestBody Map<String, String> modelMap,
            Pageable pageable
    ) {
        if (modelMap.containsKey("phone"))
            return ResponseEntity.ok(
                    clientSearchService.searchClientByPhone(modelMap.get("phone")));
        if (modelMap.containsKey("email"))
            return ResponseEntity.ok(
                    clientSearchService.searchClientByEmail(modelMap.get("email")));
        if (modelMap.containsKey("fullName"))
            return ResponseEntity.ok(
                    clientSearchService.searchClientsByFullName(modelMap.get("fullName"), pageable)
                            .getContent());
        if (modelMap.containsKey("dateOfBirth"))
            return ResponseEntity.ok(
                    clientSearchService.searchClientsByDateOfBirthIsAfter(
                                    LocalDate.parse(modelMap.get("dateOfBirth"), DateTimeFormatter.ISO_DATE), pageable)
                            .getContent());
        return ResponseEntity.badRequest().build();
    }
}

