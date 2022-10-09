import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './asset-type.reducer';

export const AssetTypeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const assetTypeEntity = useAppSelector(state => state.assetType.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="assetTypeDetailsHeading">
          <Translate contentKey="ticketSystemApp.assetType.detail.title">AssetType</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{assetTypeEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="ticketSystemApp.assetType.name">Name</Translate>
            </span>
          </dt>
          <dd>{assetTypeEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="ticketSystemApp.assetType.description">Description</Translate>
            </span>
          </dt>
          <dd>{assetTypeEntity.description}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="ticketSystemApp.assetType.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{assetTypeEntity.isActive ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/asset-type" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/asset-type/${assetTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AssetTypeDetail;
