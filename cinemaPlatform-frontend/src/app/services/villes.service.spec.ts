import { TestBed } from '@angular/core/testing';

import { VillesService } from './villes.service';

describe('VillesService', () => {
  let service: VillesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VillesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
