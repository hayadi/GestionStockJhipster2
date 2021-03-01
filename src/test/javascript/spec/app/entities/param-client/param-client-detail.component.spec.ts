import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamClientDetailComponent } from 'app/entities/param-client/param-client-detail.component';
import { ParamClient } from 'app/shared/model/param-client.model';

describe('Component Tests', () => {
  describe('ParamClient Management Detail Component', () => {
    let comp: ParamClientDetailComponent;
    let fixture: ComponentFixture<ParamClientDetailComponent>;
    const route = ({ data: of({ paramClient: new ParamClient(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamClientDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ParamClientDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParamClientDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paramClient on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paramClient).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
