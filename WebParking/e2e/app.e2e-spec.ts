import { WebParkingPage } from './app.po';

describe('web-parking App', () => {
  let page: WebParkingPage;

  beforeEach(() => {
    page = new WebParkingPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
