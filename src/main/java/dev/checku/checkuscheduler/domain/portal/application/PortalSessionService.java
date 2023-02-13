package dev.checku.checkuscheduler.domain.portal.application;

import dev.checku.checkuscheduler.domain.portal.domain.PortalSession;
import dev.checku.checkuscheduler.domain.portal.repository.SessionRedisRepository;
import dev.checku.checkuscheduler.global.error.exception.EntityNotFoundException;
import dev.checku.checkuscheduler.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortalSessionService {

    private final String SESSION = "CHECKU_SESSION_ID";
    private final SessionRedisRepository sessionRedisRepository;

    public PortalSession getPortalSession() {
        return sessionRedisRepository.findById(SESSION)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.SESSION_NOT_FOUND));
    }


}
