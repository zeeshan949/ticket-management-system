package com.zeeshan.tms.web.rest;

import com.zeeshan.tms.repository.AssetTypeRepository;
import com.zeeshan.tms.service.AssetTypeService;
import com.zeeshan.tms.service.dto.AssetTypeDTO;
import com.zeeshan.tms.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.zeeshan.tms.domain.AssetType}.
 */
@RestController
@RequestMapping("/api")
public class AssetTypeResource {

    private final Logger log = LoggerFactory.getLogger(AssetTypeResource.class);

    private static final String ENTITY_NAME = "assetType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AssetTypeService assetTypeService;

    private final AssetTypeRepository assetTypeRepository;

    public AssetTypeResource(AssetTypeService assetTypeService, AssetTypeRepository assetTypeRepository) {
        this.assetTypeService = assetTypeService;
        this.assetTypeRepository = assetTypeRepository;
    }

    /**
     * {@code POST  /asset-types} : Create a new assetType.
     *
     * @param assetTypeDTO the assetTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new assetTypeDTO, or with status {@code 400 (Bad Request)} if the assetType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/asset-types")
    public ResponseEntity<AssetTypeDTO> createAssetType(@Valid @RequestBody AssetTypeDTO assetTypeDTO) throws URISyntaxException {
        log.debug("REST request to save AssetType : {}", assetTypeDTO);
        if (assetTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new assetType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssetTypeDTO result = assetTypeService.save(assetTypeDTO);
        return ResponseEntity
            .created(new URI("/api/asset-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /asset-types/:id} : Updates an existing assetType.
     *
     * @param id the id of the assetTypeDTO to save.
     * @param assetTypeDTO the assetTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assetTypeDTO,
     * or with status {@code 400 (Bad Request)} if the assetTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the assetTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/asset-types/{id}")
    public ResponseEntity<AssetTypeDTO> updateAssetType(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AssetTypeDTO assetTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AssetType : {}, {}", id, assetTypeDTO);
        if (assetTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, assetTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assetTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AssetTypeDTO result = assetTypeService.update(assetTypeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, assetTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /asset-types/:id} : Partial updates given fields of an existing assetType, field will ignore if it is null
     *
     * @param id the id of the assetTypeDTO to save.
     * @param assetTypeDTO the assetTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assetTypeDTO,
     * or with status {@code 400 (Bad Request)} if the assetTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the assetTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the assetTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/asset-types/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AssetTypeDTO> partialUpdateAssetType(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AssetTypeDTO assetTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AssetType partially : {}, {}", id, assetTypeDTO);
        if (assetTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, assetTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assetTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AssetTypeDTO> result = assetTypeService.partialUpdate(assetTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, assetTypeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /asset-types} : get all the assetTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assetTypes in body.
     */
    @GetMapping("/asset-types")
    public ResponseEntity<List<AssetTypeDTO>> getAllAssetTypes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of AssetTypes");
        Page<AssetTypeDTO> page = assetTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /asset-types/:id} : get the "id" assetType.
     *
     * @param id the id of the assetTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assetTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/asset-types/{id}")
    public ResponseEntity<AssetTypeDTO> getAssetType(@PathVariable Long id) {
        log.debug("REST request to get AssetType : {}", id);
        Optional<AssetTypeDTO> assetTypeDTO = assetTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(assetTypeDTO);
    }

    /**
     * {@code DELETE  /asset-types/:id} : delete the "id" assetType.
     *
     * @param id the id of the assetTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/asset-types/{id}")
    public ResponseEntity<Void> deleteAssetType(@PathVariable Long id) {
        log.debug("REST request to delete AssetType : {}", id);
        assetTypeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
