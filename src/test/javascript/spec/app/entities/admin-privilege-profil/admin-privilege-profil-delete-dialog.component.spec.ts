import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { AdminPrivilegeProfilDeleteDialogComponent } from 'app/entities/admin-privilege-profil/admin-privilege-profil-delete-dialog.component';
import { AdminPrivilegeProfilService } from 'app/entities/admin-privilege-profil/admin-privilege-profil.service';

describe('Component Tests', () => {
  describe('AdminPrivilegeProfil Management Delete Component', () => {
    let comp: AdminPrivilegeProfilDeleteDialogComponent;
    let fixture: ComponentFixture<AdminPrivilegeProfilDeleteDialogComponent>;
    let service: AdminPrivilegeProfilService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminPrivilegeProfilDeleteDialogComponent],
      })
        .overrideTemplate(AdminPrivilegeProfilDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdminPrivilegeProfilDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminPrivilegeProfilService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
