package dev.checku.checkuscheduler.domain.portal.repository;

import dev.checku.checkuscheduler.domain.portal.domain.PortalSession;
import org.springframework.data.repository.CrudRepository;

public interface SessionRedisRepository extends CrudRepository<PortalSession, String> {
}
